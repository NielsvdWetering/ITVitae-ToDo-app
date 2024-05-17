import { useState } from 'react';
import './Taskbar.css'

export default function NewToDoForm({open, onClose, categoryArray}) {

    const [toDoText, setToDoText] = useState("");
    const [categoryId, setCategoryId] = useState();

    const saveNewToDo = async () => {
        const newToDo = {toDoText, categoryId};
        console.log("new to do is: " + newToDo);

        await fetch("http://localhost:8080/api/v1/todo/addItem", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newToDo)
        }).then(() => {onClose})
    }

    if (!open) return null;

    return (
        <div className='new-todo-form'>

        <form>
            <label >New ToDo: </label>
            <input type="text" className='new-todo-texbox' required value={toDoText} onChange={(e) => setToDoText(e.target.value)} />

            <select onChange={(e) => setCategoryId(e.target.value)} >
                <option >Select category</option>
                {categoryArray.map((categoryOption) => (
                    <option value={categoryOption.id} >{categoryOption.name}</option>
                ))};
            </select>

            <button onClick={saveNewToDo}>Save</button>
        </form>

        <button onClick={onClose}>close</button>

        </div>
    );
}