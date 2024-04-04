package com.alaskaair.uirepository.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.alaskaair.uirepository.common.NetworkUtils
import com.alaskaair.uirepository.common.State
import com.alaskaair.uirepository.data.repository.DesignTokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * ViewModel for handling design tokens.
 *
 * This ViewModel is responsible for fetching design tokens from the repository and handling any errors that may occur.
 * It uses the [DesignTokenRepository] to fetch the design tokens.
 *
 * @property designTokenRepository The repository used to fetch design tokens.
 */
@HiltViewModel
class DesignTokenViewModel @Inject constructor(private val designTokenRepository: DesignTokenRepository) :
    ViewModel() {

    /**
     * Fetches the design token from the repository.
     *
     * This function fetches the design token from the repository and emits the result as a [State].
     * If an error occurs during the fetching, it emits an error state with the resolved error message.
     *
     * @return A flow of [State] representing the fetching process.
     */
    fun getDesignToken() = flow {
        emit(State.LoadingState)
        try {
            emit(State.DataState(designTokenRepository.getDesignToken()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkUtils.resolveError(e))
        }
    }.flowOn(Dispatchers.IO)

}