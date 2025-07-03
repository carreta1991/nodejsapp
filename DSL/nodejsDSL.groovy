job('Aplicacion Node.js DSL') {
    description('Aplicación Node JS DSL para el curso de Jenkins')

    scm {
        git {
            remote {
                url('https://github.com/macloujulian/nodejsapp.git')
            }
            branch('master')
            extensions {
                userIdentity {
                    name('josan')
                    email('prueba@pruebas.com)
                }
            }
        }
    }

    triggers {
        scm('H/7 * * * *')
    }

    wrappers {
        nodejs('nodejs')
    }

    steps {
        shell('npm install')
    }

    configure { project ->
        project / publishers << 'jenkins.plugins.slack.SlackNotifier' {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            commitInfoChoice('NONE')
        }
    }
}
