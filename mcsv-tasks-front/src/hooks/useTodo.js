import { useEffect, useReducer, useState } from 'react';
import { todoReducer } from '../todoreducer';
import axios from 'axios';

export const useTodo = () => {

	const initialState = [];


	const [todos, dispatch] = useReducer(
		todoReducer,
		initialState
	);


	const todosCount = todos.length
	const pendingTodosCount = todos.filter(todo => !todo.done).length

	const handleNewTodo = todo => {
		const action = {
			type: 'Add Todo',
			payload: todo,
		};

		axios.post("http://localhost:8080/api", {
			id:todo.id,
			description: todo.description,
			done: false,
		})
			.then((response) => {
				console.log(response);
			});

			

		dispatch(action);
	};

	const handleDeleteTodo = id => {
		const action = {
			type: 'Delete Todo',
			payload: id,
		};

		axios.delete("http://localhost:8080/api/" + id)
			.then((response) => {
				console.log(response);
			});


		dispatch(action);
	};

	const handleCompleteTodo = id => {
		const action = {
			type: 'Complete Todo',
			payload: id,
		};

		axios.post("http://localhost:8080/api/done/" + id)
			.then((response) => {
				console.log(response);
			});

		dispatch(action);
	};

	const handleUpdateTodo = (id, description, done) => {
		const action = {
			type: 'Update Todo',
			payload: {
				id,
				description,
				done
			},
		};

		const task = {id:id,  description: description, done: done };

		axios.put("http://localhost:8080/api/" + id, task)
			.then((response) => {
				console.log(response);
			});

		dispatch(action);
	};

	return {
		todos,
		todosCount,
		pendingTodosCount,
		handleNewTodo,
		handleDeleteTodo,
		handleCompleteTodo,
		handleUpdateTodo
	}
};