import './Categories.css';
import CategoryItem from './CategoryItem';

export default function Categories({categoryArray}) {

  const alphabeticalCategories = Array.from(categoryArray).sort((a, b) => {
    const nameA = a.name.toUpperCase();
    const nameB = b.name.toUpperCase();
    if (nameA < nameB) {
      return -1;
    }
    if (nameA > nameB) {
      return 1;
    }

    return 0;
  });

  return (
    <div id='categories-container'>
      <h2>Catagories</h2>
        {alphabeticalCategories.map((category) => (
          <CategoryItem category={category} />
        ))}
    </div>
    )
}