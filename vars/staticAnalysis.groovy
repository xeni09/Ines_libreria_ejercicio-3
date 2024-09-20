def call(Map config = [:]) {
    // Parámetros con valores predeterminados
    def abortPipeline = config.get('abortPipeline', false)
    def abortOnQualityGateFail = config.get('abortOnQualityGateFail', false)

    echo "Ejecución de las pruebas de calidad de código"

    // Simular el análisis estático de código
    sh 'echo "Ejecución de las pruebas de calidad de código"'

    // Esperar el resultado del Quality Gate con un timeout de 5 minutos
    timeout(time: 5, unit: 'MINUTES') {
        // Simular el resultado del Quality Gate (se puede cambiar 'ERROR' por 'OK' para simular éxito)
        def qg = [status: 'OK'] // 'OK' para éxito, 'ERROR' para fallo
        echo "Quality Gate status: ${qg.status}"

        // Evaluar el resultado del Quality Gate y los parámetros booleanos
        if (qg.status != 'OK') {
            if (abortPipeline || abortOnQualityGateFail) {
                error "Abortando el pipeline debido a la falla en el Quality Gate"
            } else {
                echo "Continuando con el pipeline a pesar de la falla en el Quality Gate"
            }
        } else {
            echo "Quality Gate aprobado"
        }
    }
}
