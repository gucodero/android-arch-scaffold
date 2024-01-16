package com.gucodero.arch_scaffold.ui.home.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gucodero.ui.compose.fragment.ComposableDialog
import com.gucodero.ui.core.util.argument
import com.gucodero.ui.core.util.putArgs

class PeopleDialog: ComposableDialog.Stateless() {

    private val peopleString: String by argument(key = "peopleString")

    @Composable
    override fun Screen() {
        ConstraintLayout(
            modifier = Modifier.padding(16.dp)
        ) {
            val (textRef, buttonRef) = createRefs()
            Text(
                text = peopleString,
                modifier = Modifier.constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Button(
                onClick = {
                    dismiss()
                },
                modifier = Modifier.constrainAs(buttonRef) {
                    top.linkTo(textRef.bottom, margin = 12.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Text(text = "Close")
            }
        }
    }

    @Preview
    @Composable
    override fun ScreenPreview() {

    }

    companion object {

        fun newInstance(
            peopleString: String
        ) = PeopleDialog().putArgs(
            "peopleString" to peopleString
        )

    }

}