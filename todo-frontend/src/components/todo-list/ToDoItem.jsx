import './ToDoList.css';

export default function ToDoItem({toDoItem, toggleDone, deleteToDo, categoryArray}) {

    var color = "#fefff2";

    if (toDoItem.categoryId) {
        categoryArray.forEach((category) => {
            if (toDoItem.categoryId == category.id) {
                color = category.color;
            }
    })};

    return (
    <div className='todo-item' style={{backgroundColor:color}} >
        <div className='todo-item-left'>
        <input type="checkbox" defaultChecked={toDoItem.completed} className='checkbox' id={toDoItem.id} onChange={() => toggleDone(toDoItem)} />
        <p className={toDoItem.completed ? 'todo-text todo-done' : 'todo-text'}>{toDoItem.toDoText}</p>
        </div>
        <div className='todo-item-right'>
        <button className='delete-button' onClick={() => deleteToDo(toDoItem)} ><img src="src/assets/Delete-button.png" alt="delete button" width="15px" height="15px"/></button>
        </div>
    </div>
    );
}