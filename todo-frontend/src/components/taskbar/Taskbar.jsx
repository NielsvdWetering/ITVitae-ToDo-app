import './Taskbar.css';
import NewToDoForm from './NewToDoForm';
import { useState } from 'react';

export default function Taskbar({categoryArray}) {
    const [formIsOpen, setFormIsOpen] = useState(false);

    return (
        <div id='taskbar-container'>
            <div className='taskbar-item-left'>
                <button className='new-todo-button' onClick={() => setFormIsOpen(true)} >
                    <p>New ToDo</p>
                </button>

                <NewToDoForm open={formIsOpen} onClose={() => setFormIsOpen(false)} categoryArray={categoryArray} />
            </div>
        </div>
    );
}