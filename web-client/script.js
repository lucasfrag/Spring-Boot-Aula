// URLs base da API
const LIVRO_API_URL = 'http://localhost:8080/api/livros';
const COMENTARIO_API_URL = 'http://localhost:8080/api/comentarios';

// Função utilitária para fazer requisições à API
async function fetchData(url, method = 'GET', body = null) {
    const options = {
        method,
        headers: { 'Content-Type': 'application/json' },
    };
    if (body) options.body = JSON.stringify(body);

    const response = await fetch(url, options);
    if (!response.ok) throw new Error(`Erro na requisição: ${response.statusText}`);
    return response.json();
}

// Função para carregar a lista de livros
async function carregarLivros() {
    try {
        const livros = await fetchData(LIVRO_API_URL);
        const tbody = document.getElementById('livros-table-body');
        tbody.innerHTML = livros.map(livro => `
            <tr>
                <td>${livro.id}</td>
                <td>${livro.nome}</td>
                <td>${livro.autor}</td>
                <td>${livro.disponivel ? 'Sim' : 'Não'}</td>
                <td>
                    <button class="btn btn-sm btn-warning" onclick="abrirModalEditar(${livro.id})">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="deletarLivro(${livro.id})">Excluir</button>
                    <button class="btn btn-sm btn-info" onclick="abrirModalComentarios(${livro.id})">Comentários</button>
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.error('Erro ao carregar livros:', error);
    }
}

// Função para cadastrar um novo livro
async function cadastrarLivro(event) {
    event.preventDefault();

    const novoLivro = {
        nome: document.getElementById('nome').value,
        autor: document.getElementById('autor').value,
        disponivel: document.getElementById('disponivel').checked,
    };

    try {
        await fetchData(LIVRO_API_URL, 'POST', novoLivro);
        alert('Livro cadastrado com sucesso!');
        carregarLivros(); // Recarrega a lista de livros
    } catch (error) {
        console.error('Erro ao cadastrar livro:', error);
    }
}

// Função para abrir o modal de edição
async function abrirModalEditar(id) {
    try {
        const livro = await fetchData(`${LIVRO_API_URL}/${id}`);
        document.getElementById('editar-id').value = livro.id;
        document.getElementById('editar-nome').value = livro.nome;
        document.getElementById('editar-autor').value = livro.autor;
        document.getElementById('editar-disponivel').checked = livro.disponivel;

        new bootstrap.Modal(document.getElementById('editarLivroModal')).show();
    } catch (error) {
        console.error('Erro ao carregar livro para edição:', error);
    }
}

// Função para salvar as alterações de um livro
async function salvarEdicaoLivro(event) {
    event.preventDefault();

    const livroAtualizado = {
        nome: document.getElementById('editar-nome').value,
        autor: document.getElementById('editar-autor').value,
        disponivel: document.getElementById('editar-disponivel').checked,
    };

    try {
        await fetchData(`${LIVRO_API_URL}/${document.getElementById('editar-id').value}`, 'PUT', livroAtualizado);
        alert('Livro atualizado com sucesso!');
        carregarLivros(); // Recarrega a lista de livros
        bootstrap.Modal.getInstance(document.getElementById('editarLivroModal')).hide();
    } catch (error) {
        console.error('Erro ao atualizar livro:', error);
    }
}

// Função para deletar um livro
async function deletarLivro(id) {
    if (confirm('Tem certeza que deseja excluir este livro?')) {
        try {
            const response = await fetch(`${LIVRO_API_URL}/${id}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert('Livro excluído com sucesso!');
                carregarLivros(); // Recarrega os comentários
            } else {
                alert('Erro ao excluir livro.');
            }
        } catch (error) {
            console.error('Erro ao excluir livro:', error);
        }
    }
}

// Função para abrir o modal de comentários
async function abrirModalComentarios(livroId) {
    document.getElementById('livro-id-comentario').value = livroId;
    await carregarComentarios(livroId);
    new bootstrap.Modal(document.getElementById('comentariosModal')).show();
}

// Função para carregar os comentários de um livro
async function carregarComentarios(livroId) {
    try {
        const comentarios = await fetchData(`${COMENTARIO_API_URL}/livro/${livroId}`);
        const comentariosList = document.getElementById('comentarios-list');
        comentariosList.innerHTML = comentarios.map(comentario => `
            <li class="list-group-item d-flex justify-content-between align-items-center">
                ${comentario.texto}
                <div>
                    <button class="btn btn-sm btn-warning" onclick="editarComentario(${comentario.id})">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="deletarComentario(${comentario.id})">Excluir</button>
                </div>
            </li>
        `).join('');
    } catch (error) {
        console.error('Erro ao carregar comentários:', error);
    }
}

// Função para adicionar um novo comentário
async function adicionarComentario(event) {
    event.preventDefault();

    const novoComentario = {
        texto: document.getElementById('texto-comentario').value,
        livro: { id: document.getElementById('livro-id-comentario').value },
    };

    try {
        await fetchData(COMENTARIO_API_URL, 'POST', novoComentario);
        alert('Comentário adicionado com sucesso!');
        carregarComentarios(novoComentario.livro.id); // Recarrega os comentários
        document.getElementById('texto-comentario').value = ''; // Limpa o campo de texto
    } catch (error) {
        console.error('Erro ao adicionar comentário:', error);
    }
}

// Função para editar um comentário
async function editarComentario(id) {
    const novoTexto = prompt('Digite o novo texto do comentário:');
    if (novoTexto) {
        try {
            await fetchData(`${COMENTARIO_API_URL}/${id}`, 'PUT', { texto: novoTexto });
            alert('Comentário atualizado com sucesso!');
            carregarComentarios(document.getElementById('livro-id-comentario').value); // Recarrega os comentários
        } catch (error) {
            console.error('Erro ao atualizar comentário:', error);
        }
    }
}

// Função para deletar um comentário
async function deletarComentario(id) {
    if (confirm('Tem certeza que deseja excluir este comentário?')) {
        try {
            const response = await fetch(`${COMENTARIO_API_URL}/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('Comentário excluído com sucesso!');
                const livroId = document.getElementById('livro-id-comentario').value;
                carregarComentarios(livroId); // Recarrega os comentários
            } else {
                alert('Erro ao excluir comentário');
            }
        } catch (error) {
            console.error('Erro ao excluir comentário:', error);
        }
    }
}

// Inicialização
document.addEventListener('DOMContentLoaded', () => {
    carregarLivros(); // Carrega a lista de livros ao carregar a página

    // Evento para cadastrar livro
    document.getElementById('cadastro-form').addEventListener('submit', cadastrarLivro);

    // Evento para salvar edição de livro
    document.getElementById('editar-livro-form').addEventListener('submit', salvarEdicaoLivro);

    // Evento para adicionar comentário
    document.getElementById('adicionar-comentario-form').addEventListener('submit', adicionarComentario);
});