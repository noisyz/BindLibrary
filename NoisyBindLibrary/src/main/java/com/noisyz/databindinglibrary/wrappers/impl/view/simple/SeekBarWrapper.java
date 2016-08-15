package com.noisyz.databindinglibrary.wrappers.impl.view.simple;

import android.widget.SeekBar;

import com.noisyz.databindinglibrary.wrappers.ObjectBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class SeekBarWrapper extends ProgressViewWrapper<SeekBar> implements SeekBar.OnSeekBarChangeListener {
    public SeekBarWrapper(SeekBar seekBar) {
        super(seekBar);
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        bindObject(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
