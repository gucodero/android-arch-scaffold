package com.gucodero.arch_scaffold.ui.home

import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gucodero.arch_scaffold.R
import com.gucodero.arch_scaffold.ui.home.dialog.PeopleDialog
import com.gucodero.ui.compose.fragment.AppComposeFragment
import com.gucodero.ui.compose.util.OnUiEvent
import com.gucodero.ui.compose.util.uiState
import com.gucodero.ui.core.util.fullscreen
import com.gucodero.ui.core.util.isCancelable
import com.gucodero.ui.core.util.viewModelCreator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: AppComposeFragment.Stateful<HomeViewModel>() {

    override fun getViewModelCreator() = viewModelCreator()

    @Composable
    override fun Screen() {
        val uiState by uiState()
        OnUiEvent {
            when(it) {
                is HomeUIEvent.DecrementNotValid -> {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage("No se puede decrementar mas")
                        .setPositiveButton("Ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                is HomeUIEvent.People -> {
                    PeopleDialog.newInstance(
                        peopleString = it.data
                    ).isCancelable(false).fullscreen().show()
                }
            }
        }
        ConstraintLayout {
            val (
                buttonPeopleRef,
                iconRef,
                titleRef,
                counterRef,
                plusRef,
                minusRef
            ) = createRefs()
            val guidelineCenter = createGuidelineFromStart(0.5f)
            Image(
                painter = painterResource(id = R.drawable.ic_arch_scaffold),
                contentDescription = null,
                modifier = Modifier.constrainAs(iconRef) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }
            )
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(iconRef.top)
                    bottom.linkTo(iconRef.bottom)
                    start.linkTo(iconRef.end, margin = 12.dp)
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = uiState.counter.toString(),
                modifier = Modifier.constrainAs(counterRef) {
                    top.linkTo(titleRef.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                fontSize = 48.sp
            )
            Button(
                onClick = {
                    viewModel.onDecrement()
                },
                modifier = Modifier.constrainAs(minusRef) {
                    top.linkTo(counterRef.bottom, margin = 12.dp)
                    end.linkTo(guidelineCenter, margin = 6.dp)
                }
            ) {
                Text(text = "-")
            }
            Button(
                onClick = {
                    viewModel.onIncrement()
                },
                modifier = Modifier.constrainAs(plusRef) {
                    top.linkTo(counterRef.bottom, margin = 12.dp)
                    start.linkTo(guidelineCenter, margin = 6.dp)
                }
            ) {
                Text(text = "+")
            }
            Button(
                onClick = {
                    viewModel.getNewPeople()
                },
                modifier = Modifier.constrainAs(buttonPeopleRef) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Text(text = "Get new people")
            }
        }
    }

    @Preview
    @Composable
    override fun ScreenPreview() {

    }
}