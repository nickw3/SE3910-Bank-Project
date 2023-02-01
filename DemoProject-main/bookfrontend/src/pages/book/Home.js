import React, { useEffect, useState} from 'react';
import BookItem from '../../components/BookItem';
 
function Home() {

  const[books, setBooks] = useState([]);

  useEffect(()=>{
      fetch("http://localhost:8080/book", {method:"GET"})
      .then(res => res.json())
      .then(res=> {setBooks(res);})
  },[])

  return (
    <div>
        {books.map(book=> <BookItem key={book.id} book = {book}></BookItem> )}        
    </div>
  );
}

export default Home;
