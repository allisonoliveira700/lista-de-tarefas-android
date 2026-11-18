# Lista de Tarefas (Android)

App Android simples de lista de tarefas, feito em **Kotlin + Jetpack Compose (Material 3)**.

## Funcionalidades
- Adicionar tarefas (botão **+** ou tecla Enter do teclado)
- Marcar/desmarcar como concluída
- Apagar uma tarefa
- "Limpar feitas" remove todas as concluídas
- As tarefas ficam salvas no aparelho (SharedPreferences)
- Tema claro/escuro automático

## Como rodar
1. Abra a pasta no **Android Studio** (Ladybug ou mais novo).
2. Espere o Gradle sincronizar e clique em **Run ▶**.

## Baixar o APK sem instalar nada
Cada push na branch `main` gera o APK pelo GitHub Actions:
**Actions → Build APK → última execução → Artifacts → `ListaDeTarefas-debug-apk`**.

## Estrutura
```
app/src/main/java/com/exemplo/listadetarefas/
├── MainActivity.kt      # telas em Compose
└── TarefasViewModel.kt  # estado + salvamento
```

Requisitos: minSdk 24 (Android 7.0), compileSdk 35, JDK 17.
