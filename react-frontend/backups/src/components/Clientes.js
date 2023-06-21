import React from "react";
import {Table, Button, Form} from 'react-bootstrap';

class Clientes extends React.Component{

    constructor(props){
        super(props);

        this.state = {
            idCliente: '',
            nome: '',
            ativo: '',
            checkboxAtivo: false,
            clientes : []
        }
    }

    componentDidMount(){
        this.carregarClientes();
    }

    carregarClientes = () => {
        fetch("http://localhost:8080/clientes/findAll")
        .then(resposta => resposta.json())
        .then(resposta => {
            this.setState({ clientes : resposta })
        })
    }

    buscaPorIdCliente = (id) => {
        fetch("http://localhost:8080/clientes/findById/"+id)
        .then(resposta => resposta.json())
        .then(cliente => {

            var statusCheckBox = false;
            if (cliente.ativo === 'S')
                var statusCheckBox = true;
                
            this.setState({ 
                idCliente : cliente.idCliente,
                nome: cliente.nome,
                ativo: cliente.ativo,
                checkboxAtivo: statusCheckBox
            })
        })
    }

    deletarCliente = (id) => {
        fetch("http://localhost:8080/clientes/delete/"+id, { method: 'DELETE'})
        .then(resposta => {
            if(resposta.ok){
                this.carregarClientes();
            }
        })
    }

    submit = () => {

        var ativo = 'S';
        if (this.state.ativo === '' || this.state.ativo === null || this.state.ativo === 'N'){
            ativo = 'N';
        }

        const cliente = {
          idCliente: this.state.idCliente,
          nome: this.state.nome,
          ativo: ativo,
          dataCadastro: new Date()
        };
    
        if (this.state.idCliente === '') {
          this.inserirCliente(cliente);
        } else {
          this.atualizarCliente(cliente);
        }
    }

    reset = () => {
        
        this.setState(
            {
                idCliente: '',
                nome: '',
                ativo: '',
                checkboxAtivo: false
            }
        )
    }
    

    inserirCliente = (cliente) => {
        fetch("http://localhost:8080/clientes/create", 
            { 
                method: 'POST',
                headers: {'Content-Type' : 'application/json'},
                body: JSON.stringify(cliente)
            }
        )
        .then(resposta => {
            if(resposta.ok){
                this.carregarClientes();
            }else{
                alert('Não foi possível adicionar o cliente!')
            }
        })
    }

    atualizarCliente(cliente) {
        fetch('http://localhost:8080/clientes/update', {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(cliente)
        }).then((resposta) => {
          if (resposta.ok) {
            this.carregarClientes();
            //this.fecharModal();
          } else {
            alert(JSON.stringify(resposta));
          }
        });
      }
    

    atualizaNome = (e) => {
        this.setState(
            {
                nome: e.target.value
            }
        )
    }

    atualizaAtivo = (e) => {
        var checked = 'S';
        if ( ! e.target.checked){
            checked = 'N';
        }

        this.setState(
            {
                ativo: checked
            }
        )
    }

    renderModal() {
        return (
          <Form id="modalForm" onSubmit={this.submit}>
            <Form.Group className="mb-3"  controlId="formId">
              <Form.Label>Código</Form.Label>
              <Form.Control type="text" placeholder="Código" value={this.state.idCliente} readOnly={true}/>
            </Form.Group>
            <Form.Group className="mb-3"  controlId="formNome">
              <Form.Label>Nome</Form.Label>
              <Form.Control type="text" placeholder="Digite o nome" value={this.state.nome} onChange={this.atualizaNome}/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formAtivo">
              <Form.Check type="checkbox" label="Ativo" checked={this.state.checkboxAtivo} onClick={this.atualizaAtivo}/>
            </Form.Group>
            <Button variant="primary" type="submit" onClick={this.reset}>
              Novo
            </Button>
            <Button variant="primary" type="submit">
              Gravar
            </Button>
          </Form>
        );
    }


    renderTabela(){
        return (
            <Table  striped bordered hover>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Ativo</th>
                        <th>Ação</th>
                    </tr>
                </thead>
                <tbody>
                {
                    this.state.clientes.map((cliente) =>
                        <tr key={cliente.idCliente}>
                            <td>{cliente.idCliente}</td>
                            <td>{cliente.nome}</td>
                            <td>{cliente.ativo}</td>
                            <td>
                                <Button variant="secondary"onClick={() => this.buscaPorIdCliente(cliente.idCliente)}>Alterar</Button> 
                                <Button variant="danger" onClick={() => this.deletarCliente(cliente.idCliente)}>Excluir</Button>
                            </td>
                        </tr>
                    )
                }
                </tbody>
            </Table>
        )
    }

    render(){
        return (
            <div>
                {this.renderModal()}
                {this.renderTabela()}
            </div>
        )
    }

}

export default Clientes;