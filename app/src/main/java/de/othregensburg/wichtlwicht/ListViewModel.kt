package de.othregensburg.wichtlwicht

import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    var originalParticipantList: MutableList<String> = mutableListOf()
    var wichtlpartnerList: MutableList<String> = mutableListOf()
    var tasksList: MutableList<String> = mutableListOf()
    var tasksListshuffle: MutableList<String> = mutableListOf()
    var indexParticipant : Int = 0
}
