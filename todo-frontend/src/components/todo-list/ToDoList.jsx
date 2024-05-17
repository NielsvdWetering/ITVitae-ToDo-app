import './ToDoList.css';
import ToDoItem from './ToDoItem';

export default function ToDoList({toDoArray, toggleDone, loading, deleteToDo, categoryArray}) {

    const orderedToDoList = Array.from(toDoArray).sort((a, b) => a.itemIndex - b.itemIndex)

    console.log(orderedToDoList)

    if(loading) {return <div className='todo-container'>loading...</div>}

    return (
        <div className='todo-container'>
                {orderedToDoList.map((toDo) => (
                    <ToDoItem toDoItem={toDo} toggleDone={toggleDone} deleteToDo={deleteToDo} categoryArray={categoryArray} />
                ))}
        </div>
    );
}
