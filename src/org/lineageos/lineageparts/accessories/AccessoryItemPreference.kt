/*
 * SPDX-FileCopyrightText: 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.lineageparts.accessories

import android.content.Context
import android.util.AttributeSet
import androidx.preference.Preference
import lineageos.accessories.AccessoryInfo
import lineageos.accessories.AccessoryType
import lineageos.accessories.BatteryInfo
import lineageos.accessories.IAccessory
import lineageos.accessories.IAccessoryCallback
import org.lineageos.lineageparts.R

class AccessoryItemPreference @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : Preference(context, attrs) {
    private val accessoryCallback = object : IAccessoryCallback.Stub() {
        override fun onAccessoryInfoUpdated(accessoryInfo: AccessoryInfo?) {
            accessoryInfo?.let {
                title = it.displayName
                summary = it.displayId
                setIcon(
                    when (it.type) {
                        AccessoryType.KEYBOARD -> R.drawable.ic_keyboard
                        AccessoryType.ACTIVE_PEN -> R.drawable.ic_ink_pen
                        else -> R.drawable.ic_question_mark
                    }
                )
            }
        }

        override fun onAccessoryStatusUpdated(status: Byte) {

        }

        override fun onBatteryInfoUpdated(batteryInfo: BatteryInfo?) {

        }
    }

    var accessory: IAccessory? = null
        set(value) {
            field?.unregisterCallback(accessoryCallback)

            field = value

            value?.registerCallback(accessoryCallback)
        }

    init {
        setOnPreferenceClickListener {
            accessory?.let {
                // TODO open accessory settings
            }

            true
        }
    }

    override fun onAttached() {
        super.onAttached()

        accessory?.registerCallback(accessoryCallback)
    }

    override fun onDetached() {
        super.onDetached()

        accessory?.unregisterCallback(accessoryCallback)
    }
}
