package com.example.getsetgoal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

public class MilestonesFragment extends Fragment {

    String name;
    int id;
    SQLiteDatabase database;
    ArrayList<MilestoneModel> milestonedata = new ArrayList<>();
    TextView gname;
    RecyclerView msdata;

    View view;
    Motionpathadapter motionpathadapter;

    int completedms=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_milestones, container, false);
        gname = view.findViewById(R.id.fianltitle);
        msdata = view.findViewById(R.id.finalrecycler);
        name = getArguments().getString("name");
        gname.setText("Goal - " + name);
        id = getArguments().getInt("id");
        Log.v("currentfragment",name+":"+id);
        database = requireActivity().openOrCreateDatabase("goalDb", Context.MODE_PRIVATE, null);
        Cursor cur1 = database.rawQuery("select * from MilestoneDetails where Goal_id='" + id + "' ", null);
        if (cur1 != null) {
            int milestonenumberindex = cur1.getColumnIndex("Milestone_Number");
            int milestonetextindex = cur1.getColumnIndex("Milestone_Text");
            int milestonedaysindex = cur1.getColumnIndex("Milestone_Days");
            int milestonestartdateindex = cur1.getColumnIndex("Milestone_Startdate");
            int milestoneenddateindex = cur1.getColumnIndex("Milestone_Enddate");
            int milestoneIscompletedIndex = cur1.getColumnIndex("Milestone_Iscomplete");
            int milestoneIsplayedIndex = cur1.getColumnIndex("Milestone_Isplayed");
            int milestoneStatusindex = cur1.getColumnIndex("Milestone_Status");
            int milestoneTimeindex = cur1.getColumnIndex("Milestone_Time");
            while (cur1.moveToNext()) {
                int milestonenumber = cur1.getInt(milestonenumberindex);
                String milestonetext = cur1.getString(milestonetextindex);
                int milestonedays = cur1.getInt(milestonedaysindex);
                String milestonestartdate = cur1.getString(milestonestartdateindex);
                String milestoneenddate = cur1.getString(milestoneenddateindex);
                int milestoneIscompleted = cur1.getInt(milestoneIscompletedIndex);
                int milestoneIsplayed = cur1.getInt(milestoneIsplayedIndex);
                completedms=completedms+milestoneIscompleted;
                String milestoneStatus = cur1.getString(milestoneStatusindex);
                String milestoneTime = cur1.getString(milestoneTimeindex);
                milestonedata.add(new MilestoneModel(name,milestonenumber, milestonedays, milestonetext, milestonestartdate, milestoneenddate,milestoneIscompleted,milestoneIsplayed,milestoneStatus,milestoneTime));
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
            linearLayoutManager.setStackFromEnd(true);
            linearLayoutManager.setReverseLayout(true);
            msdata.setLayoutManager(linearLayoutManager);

            motionpathadapter = new Motionpathadapter(getActivity(),milestonedata, new Motionpathadapter.MilestoneInterface(){
                @Override
                public void onmilestoneUpdate(final MilestoneModel milestoneModel){
                    ContentValues cv = new ContentValues();
                    cv.put("Milestone_Iscomplete",milestoneModel.getMilestone_iscomplete()); //These Fields should be your String values of actual column names
                    cv.put("Milestone_Isplayed",milestoneModel.getMilestoneIsplayed()); //These Fields should be your String values of actual column names
                    cv.put("Milestone_Status",milestoneModel.getMilestoneStatus()); //These Fields should be your String values of actual column names
                    cv.put("Milestone_Time",milestoneModel.getMilestoneTime()); //These Fields should be your String values of actual column names
                    database.update("MilestoneDetails", cv, "Milestone_Number="+milestoneModel.getMilestoneNumber()+" AND "+"Goal_id="+id, null);
                }

                @Override
                public void onmilestonePlayStatusUpdate(MilestoneModel milestoneModel) {
                    ContentValues cv = new ContentValues();
                    cv.put("Milestone_Isplayed",milestoneModel.getMilestoneIsplayed()); //These Fields should be your String values of actual column names
                    database.update("MilestoneDetails", cv, "Milestone_Number="+milestoneModel.getMilestoneNumber()+" AND "+"Goal_id="+id, null);
                }
            },(milestonedata.size()==completedms) ? true : false);
            msdata.setAdapter(motionpathadapter);
            msdata.post(new Runnable() {
                @Override
                public void run() {
                    int inCompletePos=0;
                    boolean isAllComplete=true;
                    for(MilestoneModel model: milestonedata){
                        if(model.getMilestone_iscomplete()==0) {
                            inCompletePos=milestonedata.indexOf(model);
                            isAllComplete=false;
                            break;
                        }
                    }
                    if(isAllComplete) {
                        msdata.smoothScrollToPosition(milestonedata.size()-1);
                    }
                    else {
                        msdata.smoothScrollToPosition(inCompletePos);
                    }
                }
            });

        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static MilestonesFragment addfrag(String val, int val2) {
        MilestonesFragment fragment = new MilestonesFragment();
        Bundle args = new Bundle();
        args.putString("name", val);
        args.putInt("id", val2);
        fragment.setArguments(args);

        Log.v("fragments",val+":"+val2);
        return fragment;
    }

}