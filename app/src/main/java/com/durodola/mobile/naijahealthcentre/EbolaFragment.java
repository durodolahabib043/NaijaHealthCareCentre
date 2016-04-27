package com.durodola.mobile.naijahealthcentre;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by mobile on 2016-04-26.
 */
public class EbolaFragment extends AbstractHealthFragment {
    TextView ebolaTextview, whoresponse, prevention, treatment, transmission, aboutebola;
    TextView ebolaTextviewM, whoresponseM, preventionM, treatmentM, transmissionM, aboutebolaM;
    EbolaFragment ebolaFragment;
    VideoView videoView;

    public EbolaFragment() {
        // Required empty public constructor
    }


    public static EbolaFragment newInstance() {
        return new EbolaFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ebola_layout, container, false);
        aboutebola = (TextView) view.findViewById(R.id.what_you_need);
        ebolaTextview = (TextView) view.findViewById(R.id.eboladef);
        whoresponse = (TextView) view.findViewById(R.id.whodef);
        prevention = (TextView) view.findViewById(R.id.preventiondef);
        transmission = (TextView) view.findViewById(R.id.transmissiondef);
        treatment = (TextView) view.findViewById(R.id.treatmentdef);
        //main
        treatmentM = (TextView) view.findViewById(R.id.treatment);
        ebolaTextviewM = (TextView) view.findViewById(R.id.whatsebola);
        whoresponseM = (TextView) view.findViewById(R.id.who);
        preventionM = (TextView) view.findViewById(R.id.prevention);
        transmissionM = (TextView) view.findViewById(R.id.transmission);
        videoView = (VideoView) view.findViewById(R.id.videoView1);
        videoView.setVideoPath(
                "http://www.ebookfrenzy.com/android_book/movie.mp4");
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);
        // Uri uri = Uri.parse("https://www.youtube.com/watch?v=QzP7aWIRGjE");
        videoView.setMediaController(mediaController);
        videoView.setOnPreparedListener(new
                                                MediaPlayer.OnPreparedListener() {
                                                    @Override
                                                    public void onPrepared(MediaPlayer mp) {
                                                        // Log.i(TAG, "Duration = " +
                                                        //         videoView.getDuration());
                                                    }
                                                });
        // videoView.setVideoURI(uri);
        videoView.requestFocus();

        videoView.start();


        /*
        *    final VideoView videoView =
                (VideoView) findViewById(R.id.videoView1);

        videoView.setVideoPath(
                "http://www.ebookfrenzy.com/android_book/movie.mp4");

        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new
                MediaPlayer.OnPreparedListener()  {
                     @Override
                     public void onPrepared(MediaPlayer mp) {
                              Log.i(TAG, "Duration = " +
					videoView.getDuration());
                     }
         });

        videoView.start();*/


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        aboutebola.setTextColor(Color.parseColor("#FFFFFF"));
        transmissionM.setTextColor(Color.parseColor("#FFFFFF"));
        ebolaTextviewM.setTextColor(Color.parseColor("#FFFFFF"));
        whoresponseM.setTextColor(Color.parseColor("#FFFFFF"));
        preventionM.setTextColor(Color.parseColor("#FFFFFF"));

        ebolaTextview.setText(getContext().getResources().getString(R.string.meaningebola));
        //.getString(R.string.meaningebola));
        transmission.setText(getContext().getResources().getString(R.string.transmissiondef));
        whoresponse.setText(getContext().getResources().getString(R.string.whodef));
        prevention.setText(getContext().getResources().getString(R.string.preventiondef));
        treatment.setText(getContext().getResources().getString(R.string.treatmentdef));
    /*    videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaController mediaController = new MediaController(getContext());
                mediaController.setAnchorView(videoView);
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=QzP7aWIRGjE");
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri);
                videoView.requestFocus();

                videoView.start();
            }
        });*/


    }
}
