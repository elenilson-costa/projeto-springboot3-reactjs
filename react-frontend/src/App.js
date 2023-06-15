import Home from './components/Home';
import Sobre from './components/Sobre';
import Clientes from './components/Clientes';
import { BrowserRouter, Routes, Link, Route } from 'react-router-dom';
import {Nav} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <h1>Aplicação React</h1>
      <BrowserRouter>

        <Nav variant='tabs'>
          <Nav.Link as={Link} to="/">Página Inicial</Nav.Link>
          <Nav.Link as={Link} to="/clientes">Clientes</Nav.Link>
          <Nav.Link as={Link} to="/sobre">Sobre</Nav.Link>
        </Nav>
        {/* <ul>
          <li><Link to="/">Página iniciak</Link></li>
          <li><Link to="/clientes">Cadastro de alunos</Link></li>
          <li><Link to="/sobre">Sobre</Link></li>
        </ul> */}
      
        <Routes>
          <Route path="/" exact={true} element={<Home/>}/>
          <Route path="/clientes" element={<Clientes/>}/>
          <Route path="/sobre" element={<Sobre/>}/>
        </Routes>
      
      </BrowserRouter>
    </div>
  );
}

export default App;
