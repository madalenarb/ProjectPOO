Criem um projeto chamado AntColony no eclipse (ou noutro editor). Depois, dentro do diretório do AntColony, vão até à pasta src criada pelo editor (se o vosso editor não criar
esta pasta automaticamente, criem vocês por favor). Nesta pasta insiram todos os ficheiros e pastas que estão dentro da pasta AntColonyUpdated (a pasta onde se encontra o 
ficheiro que estão neste momento a ler). Enjoy :)


# Change directory to the local repository
$ cd /path/to/local/repository

# Check the status of your repository (optional)
$ git status

# Stage the changes you want to commit
# <file> refers to a specific file or use '.' to add all changes
$ git add <file> or git add .

# Commit the changes with a descriptive message
$ git commit -m "Commit message"

# Push the changes to the default branch
$ git push

# OR

# Create a new branch or switch to an existing branch
$ git checkout -b <branch_name> or git checkout <branch_name>

# Stage and commit your changes
$ git add <file> or git add .
$ git commit -m "Commit message"

# Push the changes to the specified branch
$ git push origin <branch_name>

# See branches
$ git branch
