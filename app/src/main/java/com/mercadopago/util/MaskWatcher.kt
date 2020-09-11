package com.mercadopago.util

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern


class MaskWatcher() : TextWatcher {
    private var isRunning = false
    private var isDeleting = false
    private var mask = "######.##"

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        if (isRunning) {
            return
        }
        isRunning = true

        if (isDeleting) {
            if (editable.length > 0) {
                if (editable[0] == '.') {
                    editable.insert(0, "0")
                } else if (editable.length == mask.length - 1) {
                    editable.insert(mask.length - 3, ".")
                }
            }
        } else {
            // editableLength points to last char
            val editableLength = editable.length

            if (editableLength < mask.length) {
                if (mask[editableLength] != '#') {
                    editable.append(mask[editableLength])
                } else if (mask[editableLength - 1] != '#') {
                    editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
                }
            } else if (editableLength > mask.length) {
                editable.delete(editableLength - 1, editableLength)
                // Verify mask consistency
                for (i in 0..editableLength) {
                    if (mask[i] != '#' && mask[i] != editable[i]) {
                        editable.replaceRange(i, i, mask[i].toString())
                    }
                }
            }
        }

        isRunning = false
    }
}