import React from "react";
import {Table, Button, Form, Modal} from 'react-bootstrap';

class Clientes extends React.Component{

    constructor(props){
        super(props);

        this.state = {
            idCliente: '',
            nome: '',
            ativo: '',
            checkboxAtivo: false,
            clientes : [],
            showFormModal : false,
            showDeleteModal: false,
            formTitle: ''
        }
    }

    componentDidMount(){
        this.loadGridClientes();
    }

    loadGridClientes = () => {
        fetch("http://localhost:8080/clientes/findAll")
        .then(resposta => resposta.json())
        .then(resposta => {
            this.setState({ clientes : resposta })
        })
    }

    openUpdateForm = (id) => {

        this.setState(
            {
                showFormModal: true,
                formTitle: 'Atualizar'
            }
        )

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
          this.apiInsertCliente(cliente);
        } else {
          this.apiUpdateCliente(cliente);
        }

        this.closeForm();
    }

    resetForm = () => {
        
        this.setState(
            {
                idCliente: '',
                nome: '',
                ativo: '',
                checkboxAtivo: false
            }
        )
    }
    

    apiInsertCliente = (cliente) => {
        fetch("http://localhost:8080/clientes", 
            { 
                method: 'POST',
                headers: {'Content-Type' : 'application/json'},
                body: JSON.stringify(cliente)
            }
        )
        .then(resposta => {
            if(resposta.ok){
                this.loadGridClientes();
            }else{
                alert('Não foi possível adicionar o cliente!')
            }
        })
    }

    apiUpdateCliente(cliente) {
        fetch('http://localhost:8080/clientes', {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(cliente)
        }).then((resposta) => {
          if (resposta.ok) {
            this.loadGridClientes();
          } else {
            alert(JSON.stringify(resposta));
          }
        });
    }
    
    apiDeleteCliente = () => {
        fetch("http://localhost:8080/clientes/"+this.state.idCliente, { method: 'DELETE'})
        .then(resposta => {
            if(resposta.ok){
                this.loadGridClientes();
            }
        })
        this.closeDeleteModal();
    }


    onChangeNome = (e) => {
        this.setState(
            {
                nome: e.target.value
            }
        )
    }

    onChangeAtivo = (e) => {
        var checked = 'S';
        if ( ! e.target.checked){
            checked = 'N';
        }

        this.setState(
            {
                ativo: checked,
                checkboxAtivo: e.target.checked
            }
        )
    }

    closeForm = () => {

        this.resetForm();

        this.setState(
            {
                showFormModal : false
            }
        )
    }

    openInsertForm = () => {
        this.resetForm();
        this.setState(
            {
                showFormModal : true,
                formTitle: 'Inserir'
            }
        )
    }

    openDeleteModal = (id) => (
        this.setState(
            {
                idCliente: id,
                showDeleteModal: true
            }
        )
    )

    closeDeleteModal = () => (
        this.setState(
            {
                showDeleteModal: false
            }
        )
    )

    renderFormDeleteItem(){
        return(
            <Modal show={this.state.showDeleteModal} onHide={this.closeDeleteModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Excluir</Modal.Title>
                </Modal.Header>
                <Modal.Body>Confirma exclusão ?</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={this.closeDeleteModal}>
                        Cancela
                    </Button>
                    <Button variant="danger"  onClick={this.apiDeleteCliente}>
                        Excluir
                    </Button>
                </Modal.Footer>
            </Modal>
        );
    }

    renderForm() {
        return (

            <Modal show={this.state.showFormModal} onHide={this.closeForm}>
            <Modal.Header closeButton>
                <Modal.Title></Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form id="modalForm" onSubmit={this.submit}>
                    <Form.Group className="mb-3"  controlId="formId">
                        <Form.Label>Código</Form.Label>
                        <Form.Control type="text" placeholder="Código" value={this.state.idCliente} readOnly={true}/>
                    </Form.Group>
                    <Form.Group className="mb-3"  controlId="formNome">
                        <Form.Label>Nome</Form.Label>
                        <Form.Control type="text" placeholder="Digite o nome" value={this.state.nome} onChange={this.onChangeNome}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formAtivo">
                        <Form.Check type="checkbox" label="Ativo" checked={this.state.checkboxAtivo} defaultChecked={this.state.checkboxAtivo} onChange={this.onChangeAtivo}/>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={this.closeForm}>
                Fechar
              </Button>
              <Button variant="primary" onClick={this.submit}>
                Gravar
              </Button>
            </Modal.Footer>
          </Modal>
            

        );
    }


    renderTabela(){
        return (
            <div>
                <Button variant="primary" type="submit" onClick={this.openInsertForm}>
                    Novo
                </Button>
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
                                    <Button variant="secondary"onClick={() => this.openUpdateForm(cliente.idCliente)}>Alterar</Button> 
                                    <Button variant="danger" onClick={() => this.openDeleteModal(cliente.idCliente)}>Excluir</Button>
                                </td>
                            </tr>
                        )
                    }
                    </tbody>
                </Table>
            </div>
        )
    }

    render(){
        return (
            <div>
                {this.renderFormDeleteItem()}
                {this.renderForm()}
                {this.renderTabela()}
            </div>
        )
    }

}

export default Clientes;