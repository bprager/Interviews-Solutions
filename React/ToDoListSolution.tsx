import React, { useState, useEffect } from 'react';

const TodoApp = () => {
  // Initialize todos from local storage if available
  const [todos, setTodos] = useState(
    JSON.parse(localStorage.getItem('todos')) || []
  );
  const [input, setInput] = useState('');

  // Update local storage when todos change
  useEffect(() => {
    localStorage.setItem('todos', JSON.stringify(todos));
  }, [todos]);

  const handleAddTodo = () => {
    if (!input.trim()) return; // Prevent adding empty todos
    const newTodo = { text: input, completed: false };
    setTodos([...todos, newTodo]);
    setInput(''); // Clear input field after adding
  };

  const toggleTodo = (index) => {
    const newTodos = todos.map((todo, i) =>
      i === index ? { ...todo, completed: !todo.completed } : todo
    );
    setTodos(newTodos);
  };

  const deleteTodo = (index) => {
    const newTodos = todos.filter((_, i) => i !== index);
    setTodos(newTodos);
  };

  const handleEdit = (index, newText) => {
    const newTodos = todos.map((todo, i) =>
      i === index ? { ...todo, text: newText } : todo
    );
    setTodos(newTodos);
  };

  return (
    <div>
      <input
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyPress={(e) => e.key === 'Enter' && handleAddTodo()}
        placeholder="Add a new todo"
      />
      <button onClick={handleAddTodo}>Add Todo</button>
      <ul>
        {todos.map((todo, index) => (
          <li key={index} style={{ textDecoration: todo.completed ? 'line-through' : 'none' }}>
            <span onClick={() => toggleTodo(index)}>{todo.text}</span>
            <button onClick={() => deleteTodo(index)}>Delete</button>
            <button onClick={() => {
              const newText = prompt('Edit todo:', todo.text);
              if (newText) {
                handleEdit(index, newText);
              }
            }}>Edit</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TodoApp;

