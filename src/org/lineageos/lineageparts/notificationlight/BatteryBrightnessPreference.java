<!--
     SPDX-FileCopyrightText: 2017-2022 The LineageOS Project
     SPDX-License-Identifier: Apache-2.0
-->

package org.lineageos.lineageparts.notificationlight;

import android.content.ContentResolver;
import android.content.Context;
import android.os.UserHandle;
import android.util.AttributeSet;

import lineageos.providers.LineageSettings;

public class BatteryBrightnessPreference extends BrightnessPreference {
    private static final String TAG = "BatteryBrightnessPreference";

    private final ContentResolver mResolver;

    public BatteryBrightnessPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mResolver = context.getContentResolver();
    }

    @Override
    protected int getBrightnessSetting() {
        return LineageSettings.System.getIntForUser(mResolver,
                LineageSettings.System.BATTERY_LIGHT_BRIGHTNESS_LEVEL,
                LIGHT_BRIGHTNESS_MAXIMUM, UserHandle.USER_CURRENT);
    }

    @Override
    protected void setBrightnessSetting(int brightness) {
        LineageSettings.System.putIntForUser(mResolver,
                LineageSettings.System.BATTERY_LIGHT_BRIGHTNESS_LEVEL,
                brightness, UserHandle.USER_CURRENT);
    }
}
