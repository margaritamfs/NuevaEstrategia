package com.example.nuevaestrategia

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.nuevaestrategia.room_database.ToDo
import com.example.nuevaestrategia.room_database.ToDoDataBase
import com.example.nuevaestrategia.room_database.repository.ToDoRepository
import com.example.nuevaestrategia.room_database.viewmodel.ToDoViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ToDoFragment : Fragment() {
    private lateinit var listRecyclerView : RecyclerView
    private lateinit var myAdapter : RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>
    var myTaskTitles : ArrayList<String> = ArrayList()
    var myTaskTimes : ArrayList<String> = ArrayList()
    var myTaskPlaces : ArrayList<String> = ArrayList()
    var info: Bundle = Bundle()

    private lateinit var todoViewModel: ToDoViewModel
    private lateinit var todoRepository: ToDoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmento=inflater.inflate(R.layout.fragment_to_to,container,false)
        return fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab : View = requireActivity().findViewById(R.id.btnFlotanteToDo)
        fab.setOnClickListener{ view->
            val intent = Intent(activity, NewTaskActivity::class.java)
            var recursiveScope = 0
            startActivityForResult(intent,recursiveScope)
        }

        var info : Bundle =   Bundle()
        info.putStringArrayList("titles",myTaskTitles)
        info.putStringArrayList("times",myTaskTimes)
        info.putStringArrayList("places",myTaskPlaces)

        listRecyclerView = requireView().findViewById(R.id.recyclerToDoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity,info)

        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL))

        updateList()

        Log.e("lepe","onViewCreated")

    }

    fun updateList(){
        val db = ToDoDataBase.getDatabase(requireActivity())
        val dbFirebase = FirebaseFirestore.getInstance()
        val toDoDAD = db.todoDao()

        todoRepository = ToDoRepository(toDoDAD)
        todoViewModel = ToDoViewModel(todoRepository)

        var result = todoViewModel.getAllTasks()
        result.invokeOnCompletion{
            var theTasks = todoViewModel.getTheTasks()
            if (theTasks!!.size!=0) {
                var i = 0
                myTaskTitles.clear()
                myTaskTimes.clear()
                myTaskPlaces.clear()

                while (i < theTasks!!.size){
                    myTaskTitles.add(theTasks[i].title)
                    myTaskTimes.add(theTasks[i].time)
                    myTaskPlaces.add(theTasks[i].place)
                    i++
                }
                myAdapter.notifyDataSetChanged()
            }
            else {
                var tasks = mutableListOf<ToDo>()
                dbFirebase.collection("ToDo").get().addOnSuccessListener {
                    var docs = it.documents
                    if (docs.size != 0){
                        var i = 0
                        while (i < docs.size){
                            myTaskTitles.add(docs[i].get("title") as String)
                            myTaskTimes.add(docs[i].get("time") as String)
                            myTaskPlaces.add(docs[i].get("place") as String)
                            tasks.add(ToDo(0, myTaskTitles[i],myTaskTimes[i],myTaskPlaces[i]))
                            i++
                        }
                    todoViewModel.insertTask(tasks)
                    myAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

       /* runBlocking {
            launch {
                var result = toDoDAD.getAllTask()
                var i=0
                myTaskTitles.clear()
                myTaskTimes.clear()
                myTaskPlaces.clear()
                while (i< result.size){
                    myTaskTitles.add(result[i].title)
                    myTaskTimes.add(result[i].time)
                    myTaskPlaces.add(result[i].place)
                    i++
                }
                myAdapter.notifyDataSetChanged()
            }
        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0){
            if (resultCode == Activity.RESULT_OK) {
                updateList()
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}


