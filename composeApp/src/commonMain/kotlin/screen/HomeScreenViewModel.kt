package screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.Repository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import model.Data

class HomeScreenViewModel(private val repository: Repository) : ViewModel() {

    private val _data = MutableSharedFlow<List<Data>>()
    val data: SharedFlow<List<Data>> = _data

    init {
        println("Home Screen ViewModel")
        println(getData().toString())

    }

    private fun getData(): Deferred<Boolean> = viewModelScope.async {
        _data.emit(repository.getPosts(12).data)
        println(repository.getPosts(12).data)
        return@async true
    }
}