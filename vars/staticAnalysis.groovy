def call(Map config = [:]) {
    def abortPipeline = config.get('abortPipeline', false)
    def abortOnQualityGateFail = config.get('abortOnQualityGateFail', false)

    echo "Ejecución de las pruebas de calidad de código"

    // Simular el análisis estático de código
    sh 'echo "Ejecución de las pruebas de calidad de código"'

    // Esperar el resultado del Quality Gate con un timeout de 5 minutos
    timeout(time: 5, unit: 'MINUTES') {
        def qg = [status: 'ERROR'] // Cambiar a 'OK' para simular éxito
        if (qg.status != 'OK') {
            echo "Quality Gate status: ${qg.status}"
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

