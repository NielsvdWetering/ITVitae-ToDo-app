import './Categories.css';

export default function CategoryItem({category}) {
    return (
        <div className='category-item'>
        <span className='category-color' style={{color:category.color}} >•</span>
        <span className='category-item-name'>{category.name}</span>
        </div>
    )
}