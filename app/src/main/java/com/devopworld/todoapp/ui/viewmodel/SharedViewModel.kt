package com.devopworld.todoapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devopworld.todoapp.data.model.Priority
import com.devopworld.todoapp.data.model.TodoTask
import com.devopworld.todoapp.repository.DataStoreRepository
import com.devopworld.todoapp.repository.TodoRepository
import com.devopworld.todoapp.util.Action
import com.devopworld.todoapp.util.Action.*
import com.devopworld.todoapp.util.Constant.MAX_TITLE_LENGTH
import com.devopworld.todoapp.util.RequestState
import com.devopworld.todoapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val action : MutableState<Action> = mutableStateOf(NO_ACTION)

    val id : MutableState<Int> = mutableStateOf(0)
    val title : MutableState<String> = mutableStateOf("")
    val description : MutableState<String> = mutableStateOf("")
    val priority : MutableState<Priority> = mutableStateOf(Priority.LOW)

     val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

     val searchTextState: MutableState<String> =
        mutableStateOf("")


    private val _searchedTask = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val searchTask: StateFlow<RequestState<List<TodoTask>>> = _searchedTask


    private val _allTasks = MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<TodoTask>>> = _allTasks

    private val _sortState = MutableStateFlow<RequestState<Priority>>(RequestState.Idle)
    val sortState : StateFlow<RequestState<Priority>> = _sortState

    init {
        getAllTasks()
        readSortState()
    }

    fun searchDatabase(searchQuery: String) {
        _searchedTask.value = RequestState.Loading
        try {
            viewModelScope.launch {
               todoRepository.searchTask(searchQuery = "%$searchQuery%")
                   .collect { searchedTasks->
                       _searchedTask.value =RequestState.Success(searchedTasks)
                   }
            }
        }catch (e: Exception){
            _searchedTask.value = RequestState.Error(e)
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
    }

    val lowPriorityTasks : StateFlow<List<TodoTask>> =
        todoRepository.sortByLowPriority.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            emptyList()
        )

    val highPriorityTasks : StateFlow<List<TodoTask>> =
        todoRepository.sortByHighPriority.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            emptyList()
        )


    fun persisSortState(priority: Priority){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.persistSortState(priority)
        }
    }

    private fun readSortState(){
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch {
                todoRepository.getAllTasks.collect {
                    dataStoreRepository.readSortState
                        .map { Priority.valueOf(it) }
                        .collect{
                            _sortState.value = RequestState.Success(it)
                        }
                }
            }
        }catch (e: Exception){
            _sortState.value = RequestState.Error(e)
        }
    }



    private fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                todoRepository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        }catch (e: Exception){
            _allTasks.value = RequestState.Error(e)
        }

    }

    private val _selectedTask : MutableStateFlow<TodoTask?> = MutableStateFlow(null)
    val selectedTask : StateFlow<TodoTask?> = _selectedTask

    fun getSelectedTask(taskId : Int){
        viewModelScope.launch {
            todoRepository.getSelectedTask(taskId = taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }

    private fun addTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val todoTask = TodoTask(
                Title = title.value,
                description = description.value,
                priority = priority.value
            )

            todoRepository.addTask(todoTask = todoTask)
        }
        searchAppBarState.value = SearchAppBarState.CLOSED
    }

    fun handleDatabaseActions(action: Action){
        when (action){
            ADD -> addTask()
            UPDATE -> {
                updateTask();
            }
            DELETE -> {
                deleteTask()
            }
            DELETE_ALL -> {
                deleteAllTasks()
            }
            UNDO -> {
                addTask()
            }
            NO_ACTION -> {

            }
        }
    }

    private fun updateTask() {
        viewModelScope.launch(Dispatchers.IO){
            val todoTask = TodoTask(
                id= id.value,
                Title = title.value,
                description = description.value,
                priority= priority.value
            )
            todoRepository.updateTask(todoTask)
        }
    }

    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO){
            val todoTask = TodoTask(
                id= id.value,
                Title = title.value,
                description = description.value,
                priority= priority.value
            )
            todoRepository.removeTask(todoTask)
        }
    }

    private fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteAllTasks()
        }
    }


    fun updateSelectedTask(selectedTask : TodoTask?){
        if(selectedTask != null){
            id.value = selectedTask.id
            title.value = selectedTask.Title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        }else{
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun limitTitle(newString : String){
        if (newString.length < MAX_TITLE_LENGTH){
            title.value = newString
        }
    }

    fun validateFields() : Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}