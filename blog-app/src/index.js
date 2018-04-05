import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<App src="http://localhost:8080/posts/1"/>, document.getElementById('root'));
registerServiceWorker();
