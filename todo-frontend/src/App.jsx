import { useState, useEffect } from 'react';
import './App.css';
import Taskbar from './components/taskbar/Taskbar';
import Categories from './components/categories/Categories';
import ToDoList from './components/todo-list/ToDoList';

export default function App() {
  const [toDoItems, setToDoItems] = useState([]);
  const [categoryArray, setCategoryArray] = useState([]);
  const [loading, setLoading] = useState(false);
  

  useEffect(() => {
    fetchData()
  },[Taskbar])

  useEffect(() => {
    fetchCategories()
  },[])


  const deleteToDo = async (toDo) => {
    setLoading(true)
    await fetch("http://localhost:8080/api/v1/todo/"+toDo.id, {
      method: "DELETE"
    });

    fetchData()
  }
  
  const toggleDone = async (toDo) => {
    setLoading(true)
    await fetch("http://localhost:8080/api/v1/todo/toggleDone/"+toDo.id+"/"+!toDo.completed, {
      method: "PATCH"
    });

    fetchData()
  }

  const fetchData = async () => {
    setLoading(true)
    await fetch("http://localhost:8080/api/v1/todo/all-items")
    .then(res => res.json())
    .then(toDos => setToDoItems(toDos));
    setLoading(false)
  }

  const fetchCategories = async () => {
    await fetch("http://localhost:8080/api/v1/category/find-all")
    .then(res => res.json())
    .then(categories => setCategoryArray(categories));
  }

  return (
    <>
      <div className="todo-page">
        <div className="container">
            <Taskbar categoryArray={categoryArray}/>
            <Categories categoryArray={categoryArray} />
            <ToDoList toDoArray={toDoItems} toggleDone={toggleDone} loading={loading} deleteToDo={deleteToDo} categoryArray={categoryArray} />
        </div>
      </div>
    </>
  )
}

