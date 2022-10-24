# Esercizio di risoluzione di un merge conflict

**Il tempo massimo in laboratorio per questo esercizio è di _20 minuti_.
Se superato, sospendere l'esercizio e riprenderlo per ultimo!**

Si visiti https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.
Questo repository contiene due branch: `master` e `feature`

Per ognuna delle seguenti istruzioni, si annoti l'output ottenuto.
Prima di eseguire ogni operazione sul worktree o sul repository,
si verifichi lo stato del repository con `git status`.

1. Si cloni localmente il repository
```
git clone git@github.com:APICe-at-DISI/OOP-git-merge-conflict-test.git
Cloning into 'OOP-git-merge-conflict-test'...
Enter passphrase for key '/home/fra/.ssh/id_rsa':
remote: Enumerating objects: 12, done.
remote: Counting objects: 100% (4/4), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 12 (delta 1), reused 1 (delta 1), pack-reused 8
Receiving objects: 100% (12/12), done.
Resolving deltas: 100% (2/2), done.
```
```
❯ git status
On branch master
Your branch is up to date with 'origin/master'.

nothing to commit, working tree clean
```

2. Ci si assicuri di avere localmente entrambi i branch remoti
```
❯ git log --all --graph --oneline
* bed943f (origin/feature) Print author information
| * 8e0f29c (HEAD -> master, origin/master, origin/HEAD) Change HelloWorld to print the number of available processors
|/
* d956df6 Create .gitignore
* 700ee0b Create HelloWorld
```

3. Si faccia il merge di `feature` dentro `master`, ossia: si posizioni la `HEAD` su `master`
   e da qui si esegua il merge di `feature`
```
❯ git checkout master
Already on 'master'
Your branch is up to date with 'origin/master'.
❯ git merge origin/feature
Auto-merging HelloWorld.java
CONFLICT (content): Merge conflict in HelloWorld.java
Automatic merge failed; fix conflicts and then commit the result.
```

4. Si noti che viene generato un **merge conflict**!
5. Si risolva il merge conflict come segue:
   - Il programma Java risultante deve stampare sia il numero di processori disponibili
     (funzionalità presente su `master`)
     che il nome dell'autore del file
     (funzionalità presente su `feature`)

6. Si crei un nuovo repository nel proprio github personale
7. Si aggiunga il nuovo repository creato come **remote** e si elenchino i remote
```
❯ git remote rm origin
❯ git remote add origin git@github.com:fraa-unibo/oop-lab06.git
❯ git remote -v
origin  git@github.com:fraa-unibo/oop-lab06.git (fetch)
origin  git@github.com:fraa-unibo/oop-lab06.git (push)
```

8. Si faccia push del branch `master` sul proprio repository
```
❯ git push origin
fatal: The current branch master has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin master
```

9. Si setti il branch remoto `master` del nuovo repository come *upstream* per il proprio branch `master` locale
```
git push --set-upstream origin master
Enter passphrase for key '/home/fra/.ssh/id_rsa':
Enumerating objects: 9, done.
Counting objects: 100% (9/9), done.
Delta compression using up to 16 threads
Compressing objects: 100% (6/6), done.
Writing objects: 100% (9/9), 1012 bytes | 34.00 KiB/s, done.
Total 9 (delta 1), reused 9 (delta 1), pack-reused 0
remote: Resolving deltas: 100% (1/1), done.
remote:
remote: Create a pull request for 'master' on GitHub by visiting:
remote:      https://github.com/fraa-unibo/oop-lab06/pull/new/master
remote:
To github.com:fraa-unibo/oop-lab06.git
 * [new branch]      master -> master
Branch 'master' set up to track remote branch 'master' from 'origin'.
```
