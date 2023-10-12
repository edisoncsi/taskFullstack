import { useRef, useState } from 'react';
import { FaEdit } from 'react-icons/fa';
import { useForm } from '../hooks/useForm';

export const TodoUpdate = ({ todo, handleUpdateTodo }) => {
	const { updateDescription, onInputChange } = useForm({
		updateDescription: todo.description,
	});

	const [disabled, setDisabled] = useState(true);
	const focusInputRef = useRef();

	const onSubmitUpdate = e => {
		e.preventDefault();
		handleUpdateTodo(todo.id, updateDescription, todo.done);
		setDisabled(!disabled);
		focusInputRef.current.focus();
	};

	return (
		<form onSubmit={onSubmitUpdate}>
			<input
				type='text'
				className={`input-update ${
					todo.done ? 'text-decoration-dashed' : ''
				}`}
				name='updateDescription'
				value={updateDescription}
				onChange={onInputChange}
				placeholder='Ingresa la tarea que vamos realizar.'
				readOnly={disabled}
				ref={focusInputRef}
			/>

			<button className='btn-edit' type='submit'>
				<FaEdit />
			</button>
		</form>
	);
};
