import React from "react";
import Table from 'react-bootstrap/Table';

class Clientes extends React.Component{

    constructor(props){
        super(props);

        this.state = {
            clientes : []
        }
    }

    componentDidMount(){
        this.carregarClientes();
    }

    carregarClientes = () => {
        fetch("http://localhost:8080/clientes/findAll")
        .then(resposta => resposta.json())
        .then(dados => {
            this.setState({ clientes : dados })
        })
    }

    render(){
        return (
            <Table  striped bordered hover>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Ativo</th>
                    </tr>
                </thead>
                <tbody>
                {
                    this.state.clientes.map((cliente) =>
                        <tr key={cliente.idCliente}>
                            <td>{cliente.idCliente}</td>
                            <td>{cliente.nome}</td>
                            <td>{cliente.ativo}</td>
                        </tr>
                    )
                }
                </tbody>
            </Table>
        )
    }

}

export default Clientes;