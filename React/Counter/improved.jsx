import React, { useState, useCallback } from "react";

function ParentComponent() {
  const [count, setCount] = useState(0);

  // Using useCallback to memoize the incrementCount function
  const incrementCount = useCallback(() => {
    setCount((prevCount) => prevCount + 1);
  }, []); // Dependency array is empty, so this function is created only once

  return (
    <div>
      <h1>Counter</h1>
      <p>Count: {count}</p> {/* Displaying the count value here */}
      <button onClick={incrementCount}>+</button>
    </div>
  );
}

export default ParentComponent;
