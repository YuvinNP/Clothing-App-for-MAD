package com.example.clothing_app_mad;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Ladies.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Ladies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Ladies extends Fragment {

    GridLayout gridMain;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ladies, container, false);

        gridMain = view.findViewById(R.id.mainGrid);

        setSingleEvent(gridMain);

        return view;



    }

    private void setSingleEvent(GridLayout gridMain) {

        //loop all child items of main grid
        for (int i = 0; i < gridMain.getChildCount(); i++){

            CardView cardView = (CardView) gridMain.getChildAt(i);

            final int index = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (index == 0){

                        Intent intent = new Intent(getActivity(), Ladies_Dresses.class);
                        startActivity(intent);
                    }
                    else if(index == 1){

                        Intent intent = new Intent(getActivity(), Ladies_Tops.class);
                        startActivity(intent);
                    }
                    else if (index == 2){

                        Intent intent = new Intent(getActivity(), Ladies_Skirts.class);
                        startActivity(intent);
                    }
                    else {

                        Intent intent = new Intent(getActivity(), Ladies_Trousers.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }


/*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Ladies() {
        // Required empty public constructor
    }

    */
/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ladies.
     *//*

    // TODO: Rename and change types and number of parameters
    public static Ladies newInstance(String param1, String param2) {
        Ladies fragment = new Ladies();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/



  /*  // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
