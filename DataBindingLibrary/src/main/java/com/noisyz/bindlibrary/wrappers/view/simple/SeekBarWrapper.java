package com.noisyz.bindlibrary.wrappers.view.simple;

import android.widget.SeekBar;

import com.noisyz.bindlibrary.wrappers.ViewBinder;

/**
 * Created by Oleg on 18.03.2016.
 */
public class SeekBarWrapper extends ViewBinder<Integer, SeekBar> implements SeekBar.OnSeekBarChangeListener {

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

    @Override
    public void addListeners(SeekBar seekBar) {
        seekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void removeListeners(SeekBar seekBar) {
        seekBar.setOnSeekBarChangeListener(null);
    }

    @Override
    public Integer getViewValue(SeekBar seekBar) {
        return seekBar.getProgress();
    }

    @Override
    public void bindUI(Integer integer, SeekBar seekBar) {
        seekBar.setProgress(integer);
    }
}
