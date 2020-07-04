/*
 * Copyright Copyright 2020 kimy csjs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kimy.budgetbuddy;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //floating_action_btn

    private FloatingActionButton fab_main_btn;
    private FloatingActionButton fab_income_btn;
    private FloatingActionButton fab_expense_btn;

    //floating_button_textView

    private TextView fab_income_txt;
    private TextView fab_expense_txt;

    //boolean_val

    private boolean isOpen=false;

    private Animation FadOpen,FadClose;

    //firebase

    private FirebaseAuth cs_Auth;
    private DatabaseReference mIncomeDatabase;
    private DatabaseReference mExpenseDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView =  inflater.inflate(R.layout.fragment_home, container, false);

        cs_Auth =FirebaseAuth.getInstance();
        FirebaseUser mUser= cs_Auth.getCurrentUser();
        String user_id = mUser.getUid();

        mIncomeDatabase= FirebaseDatabase.getInstance().getReference().child("IncomeData").child(user_id);
        mExpenseDatabase=FirebaseDatabase.getInstance().getReference().child("ExpenseDatabase").child(user_id);

       //mIncomeDatabase.keepSynced(true);
        //mExpenseDatabase.keepSynced(true);

        //Connect floating button to layout

        fab_main_btn = myView.findViewById(R.id.fb_main_plus_btn);
        fab_income_btn = myView.findViewById(R.id.income_Ft_btn);
        fab_expense_btn = myView.findViewById(R.id.expense_Ft_btn);

        //Connect floating text.

        fab_income_txt = myView.findViewById(R.id.income_ft_text);
        fab_expense_txt = myView.findViewById(R.id.expense_ft_text);

        //animation_connect

        FadOpen= AnimationUtils.loadAnimation(getActivity(),R.anim.fade_open);
        FadClose=AnimationUtils.loadAnimation(getActivity(),R.anim.fade_close);

        fab_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_data();

                if (isOpen){

                    fab_income_btn.startAnimation(FadClose);
                    fab_expense_btn.startAnimation(FadClose);
                    fab_income_btn.setClickable(false);
                    fab_expense_btn.setClickable(false);

                    fab_income_txt.startAnimation(FadClose);
                    fab_expense_txt.startAnimation(FadClose);
                    fab_income_txt.setClickable(false);
                    fab_expense_txt.setClickable(false);
                    isOpen=false;

                }else {
                    fab_income_btn.startAnimation(FadOpen);
                    fab_expense_btn.startAnimation(FadOpen);
                    fab_income_btn.setClickable(true);
                    fab_expense_btn.setClickable(true);

                    fab_income_txt.startAnimation(FadOpen);
                    fab_expense_txt.startAnimation(FadOpen);
                    fab_income_txt.setClickable(true);
                    fab_expense_txt.setClickable(true);
                    isOpen=true;

                }

            }
        });

        return myView;
    }

    private void add_data(){

        fab_income_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                income_data_insert();
            }
        });

        fab_expense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //expenseDataInsert();
            }
        });

    }

    public void income_data_insert(){

        AlertDialog.Builder my_dialog=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=LayoutInflater.from(getActivity());
        View my_view=inflater.inflate(R.layout.insert_data,null);
        my_dialog.setView(my_view);
        final AlertDialog dialog=my_dialog.create();

        final EditText edtAmount=my_view.findViewById(R.id.amount_edt);
        final EditText edtType=my_view.findViewById(R.id.type_edt);
        final EditText edtNote=my_view.findViewById(R.id.note_edt);

        Button btnSave=my_view.findViewById(R.id.btnSave);
        Button btnCancel=my_view.findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String type=edtType.getText().toString().trim();
                String amount=edtAmount.getText().toString().trim();
                String note=edtNote.getText().toString().trim();

                if (TextUtils.isEmpty(type)){
                    edtType.setError("Required Field..");
                    return;
                }

                if (TextUtils.isEmpty(amount)){
                    edtAmount.setError("Required Field..");
                    return;
                }

                int our_amount_int=Integer.parseInt(amount);

                if (TextUtils.isEmpty(note)){
                    edtNote.setError("Required Field..");
                    return;
                }

                //String id=mIncomeDatabase.push().getKey();

                //String mDate= DateFormat.getDateInstance().format(new Date());

                //Data data=new Data(our_amount_int,type,note,id,mDate);

               // mIncomeDatabase.child(id).setValue(data);

               // Toast.makeText(getActivity(),"Data ADDED",Toast.LENGTH_SHORT).show();

                //ftAnimation();
               // dialog.dismiss();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ftAnimation();
                dialog.dismiss();
            }
        });

        dialog.show();


    }

}