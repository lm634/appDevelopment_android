package uk.co.lm634.nordfeldstefn;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Context;

import static uk.co.lm634.nordfeldstefn.R.id.BackButton;
import static uk.co.lm634.nordfeldstefn.R.id.PauseButton;
import static uk.co.lm634.nordfeldstefn.R.id.PlayButton;
import static uk.co.lm634.nordfeldstefn.R.id.SkipButton;
import static uk.co.lm634.nordfeldstefn.R.id.song_list;

/**
 * Copyright Lluis Mather 2017.
 */

public class tab2fragment extends Fragment {

    private ListView songView;
    AudioManager mgr = null;
    SeekBar music = null;
    final int[] resID = {R.raw.kings_norton_birds, R.raw.kings_norton_cycle_route, R.raw.kings_norton_dogs,
            R.raw.kings_norton_geese, R.raw.kings_norton_people, R.raw.longbridge_beeps, R.raw.longbridge_cyclists,
            R.raw.longbridge_environmental_vocalisation_1, R.raw.longbridge_environmental_vocalisation_3, R.raw.longbridge_environmental_vocalisation_4,
            R.raw.longbridge_movement, R.raw.northfield_environmental_vocalisation, R.raw.northfield_sound_moment_1_children_mums,
            R.raw.northfield_sound_moment_2_children_mums, R.raw.weoley_castle_atm, R.raw.weoley_castle_duck_pond,
            R.raw.weoley_castle_soundwalk_outside, R.raw.weoley_castle_switch_box};
    int currentSongID = 0;
    MediaPlayer app1Sound;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2fragment, container, false);

        final String songs[] = {"Kings Norton Birds","Kings Norton Cycle Route","Kings Norton Dogs","Kings Norton Geese",
        "Kings Norton People","Longbridge Beeps","Longbridge Cyclists","Longbridge Environmental Vocalisation 1",
        "Longbridge Environmental Vocalisation 2","Longbridge Environmental Vocalisation 3","Longbridge Movement","Northfield Environmental Vocalisation",
        "Northfield Sound Moment 1 Children/Mums","Northfield Sound Moment 2 Children/Mums","Weoley Castle ATM",
        "Weoley Castle Duck Pond","Weoley Castle Soundwalk Outside","Weoley Castle Switch Box"};

        String textPlaceholder = "Click on a composition to start the player";
        final TextView tv = (TextView)view.findViewById(R.id.compNameTextView);
        tv.setText(textPlaceholder);

        final int stream = AudioManager.STREAM_MUSIC;
        mgr=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        music = (SeekBar)view.findViewById(R.id.volumeBar);
        music.setMax(mgr.getStreamMaxVolume(stream));
        music.setProgress(mgr.getStreamVolume(stream));
        music.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mgr.setStreamVolume(stream, progress,AudioManager.FLAG_PLAY_SOUND);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        songView = (ListView)view.findViewById(song_list);
        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (app1Sound != null) {
                    app1Sound.stop();
                }
                Object listItem = songView.getItemAtPosition(position);
                app1Sound = MediaPlayer.create(getActivity(), resID[position]);
                app1Sound.start();
                currentSongID = position;
                tv.setText((CharSequence) listItem);
            }
        });

        ImageButton pause = (ImageButton) view.findViewById(PauseButton);
        pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app1Sound != null) {
                    if (app1Sound.isPlaying()) {
                       app1Sound.pause();
                    }
                }
            }
        });

        ImageButton play = (ImageButton) view.findViewById(PlayButton);
        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app1Sound == null) {
                    app1Sound = MediaPlayer.create(getActivity(), resID[currentSongID]);
                    app1Sound.start();
                    tv.setText(songs[currentSongID]);
                } else {
                    if (!app1Sound.isPlaying()) {
                        app1Sound.start();
                        tv.setText(songs[currentSongID]);
                    }
                }
            }
        });

        ImageButton back = (ImageButton) view.findViewById(BackButton);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app1Sound == null) {
                    app1Sound = MediaPlayer.create(getActivity(),resID[currentSongID]);
                    app1Sound.start();
                    tv.setText(songs[currentSongID]);
                }
                else if (currentSongID != 0)
                    {
                        if (app1Sound.isPlaying()) {app1Sound.stop();}
                        currentSongID -= 1;
                        app1Sound = MediaPlayer.create(getActivity(),resID[currentSongID]);
                        app1Sound.start();
                        tv.setText(songs[currentSongID]);
                    }
            }
        });

        ImageButton skip = (ImageButton) view.findViewById(SkipButton);
        skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (app1Sound == null) {
                    app1Sound = MediaPlayer.create(getActivity(), resID[currentSongID]);
                    app1Sound.start();
                    tv.setText(songs[currentSongID]);
                }
                else {
                    System.out.println(currentSongID);
                    if (currentSongID < 17) {
                        if (app1Sound.isPlaying()) {
                            app1Sound.stop();
                        }
                        currentSongID += 1;
                        System.out.println("Adding1");
                        System.out.println(currentSongID);
                        app1Sound = MediaPlayer.create(getActivity(), resID[currentSongID]);
                        app1Sound.start();
                        tv.setText(songs[currentSongID]);
                    }
                }
            }
        });

        return view;

    }
}
