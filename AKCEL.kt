enum class Nivel { BASICO, INTERMEDIARIO, MASTER }

data class Usuario(val nomeUsuario: String, val matriculaUsuario: Int, var tempoCompletado: Int = 0) {
    override fun toString(): String {
        return "$nomeUsuario (matricula $matriculaUsuario, tempo completado: $tempoCompletado minutos)"
    }
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60) {
    override fun toString(): String {
        return "$nome, com duração de $duracao minutos"
    }
}

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, val nivel: Nivel) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(vararg usuario: Usuario) {
        inscritos.addAll(usuario)
        println("Usuários matriculados: ${usuario.joinToString { it.nomeUsuario }}")
    }

    fun calcularTempoRestante(usuario: Usuario): Int {
        val duracaoTotal = conteudos.sumOf { it.duracao }
        return duracaoTotal - usuario.tempoCompletado
    }

    fun listarInscritos() {
        if (inscritos.isEmpty()) {
            println("Nenhum aluno inscrito.")
        } else {
            println("Alunos inscritos:")
            inscritos.forEach { 
                println("${it} - Tempo restante: ${calcularTempoRestante(it)} minutos")
            }
        }
    }

    override fun toString(): String {
        return """
        Curso: $nome
        Conteúdo: ${conteudos.joinToString { it.toString() }}
        Nível: $nivel
        Alunos inscritos: ${inscritos.size}
        """
    }
}

fun main() {
    
    val isa = Usuario("Isadora", 111569, 30) 
    val tata = Usuario("Talyta", 132902, 60)
    val belly = Usuario("Isabelly", 122840, 90)
    val lolo = Usuario("Lorena", 123022, 180)
    val manu = Usuario("Manuelly", 103020, 45)

  
    val conteudo1 = ConteudoEducacional("Kotlin Básico", 120)
    val conteudo2 = ConteudoEducacional("Kotlin Intermediário", 180)
    val conteudo3 = ConteudoEducacional("Kotlin Avançado", 240)

   
    val formacaoKotlin = Formacao("Formação em Kotlin", listOf(conteudo1, conteudo2, conteudo3), Nivel.MASTER)

   
    formacaoKotlin.matricular(isa, tata, belly, lolo, manu)
      

    println(formacaoKotlin)
    formacaoKotlin.listarInscritos()
}

