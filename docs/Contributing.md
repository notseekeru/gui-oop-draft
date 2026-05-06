# Contributing Guide (For Everyone)

## Quick Start and Prerequisites

- Run these command below with windows `powershell`

```ps1
  winget install --id Git.Git -e --source winget

  winget install --id BellSoft.LibericaJDK.25.Full -e --source winget

  $env:JAVA_HOME

  winget install --id Microsoft.VisualStudioCode -e --source winget
```

## Cloning the Repository and Running the Project Locally

1. Open your terminal (Command Prompt, PowerShell, or Git Bash).
2. Navigate to the directory where you want to clone the repository then run:

```bash
git clone https://github.com/notseekeru/gui-oop-draft.git
cd gui-oop-draft
```

3. Open the project in VS Code:

```bash
code .
```

4. Run the project using Maven:

```bash
./mvnw clean javafx:run
./mvnw clean verify
./mvnw spotless:apply
```

## Your Task and Responsibilities

- Each team member will be assigned specific features or components to work on (e.g., login functionality, product listing, cart management).
- You are responsible for implementing your assigned features according to the project.
- Refer to the [Example_Code](docs/Example_Code.md) for examples of how to structure your code according to the layered architecture we discussed.

## GitHub Flow (How We Work — VS Code Only)

**Some Terms:**

- **Commit**: Save your changes.
- **Pull**: Get the latest code from GitHub.
- **Push**: Send your changes to GitHub.
- **Branch**: Your own copy for a task. Keeps main clean.
- **PR (Pull Request)**: Ask to add your branch to main. Needs review first.

### Step-by-Step (All in VS Code)

1. Start from a clean `main` branch.
   - **Note:** The `main` branch is protected. You cannot push directly to it. You must create a branch for your work.
   - Open the **Source Control** panel (left sidebar, or Ctrl+Shift+G).
   - Click the "..." menu (Right Beside Changes) in Source Control, choose **Pull** to get the latest code.

2. Create a branch for your task.
   - Click the branch name (bottom left), then **Create new branch**.
   - Name it like `feature/login-validation` or `fix/cart-total`.

3. Make your changes.
   - Only edit files needed for your task (usually in `src/main`).
   - Check the **Source Control** panel to see what you changed.

4. Stage and commit your changes.
   - In **Source Control**, click the `+` next to each file you want to include.
   - Write a short conventional commit message (e.g., `fix: login button validation`, `feat: add sql login functionality`).
   - Click the checkmark (✔) to commit.

5. Push your branch to GitHub.
   - Click **Publish Branch** or **Sync Changes** at the top of Source Control.

6. Open a pull request (PR).
   - Click the blue banner in VS Code or go to GitHub and click **Compare & pull request**.
   - Title = what your branch does. Describe your changes simply.
   - If you see a red X (CI/CD failed), ask for help in the group chat.

7. Merge only if PR is green and approved.
   - After merging, delete your branch (using the "..." menu). Click **Delete Branch**.

8. Start your next task.
   - Switch back to `main` (bottom left), then pull the latest code ("..." menu > **Pull**).
   - Repeat the steps above for each new feature or fix.

---

## Troubleshooting & Common Mistakes

### Set up issues or can’t run the project?

- **GDrive Link:** https://drive.google.com/drive/folders/1a6pCo_QgS-lzT49429IeU90hzxB_QM2f

* Contains the latest backup of the project files, including video demo, and tutorials or just want a clear understanding on what to do. This is a backup in case you have trouble running the project locally.

### Failsafe: Accidentally Committed to Main and not to another branch?

If you made changes or a commit on `main` by mistake (and can’t push because it’s protected), don’t worry! Here’s how to fix it:

1. **Create a new branch from your current state:**
   - Click the branch name (bottom left), choose **Create new branch**.
   - Name it for your task (e.g., `feature/your-task`).

2. **Your changes and commits will move to the new branch.**

3. **Continue working as usual:**
   - Push your new branch.
   - Open a pull request (or not if changes is not ready for main branch).

### Merge Conflict Example

Merge conflicts happen when two people change the same part of a file. Don’t panic—they’re normal in group projects! Here’s how to fix them in VS Code:

1.  You pull the latest code from `main` into your branch, and there’s a conflict because someone else changed the same file.
    - VS Code will show a message at the top: **"This branch has conflicts that must be resolved"** or you’ll see files marked as **conflicted** in Source Control.
    - The file will have special markers like:

    ```text
    <<<<<<< HEAD
    your changes here
    =======
    their changes here
    >>>>>>> branch-name
    ```

2.  Open the Conflicted File
    - Click the conflicted file in the Source Control panel.
    - VS Code will highlight the conflict and show options like **Accept Current Change**, **Accept Incoming Change**, **Accept Both Changes**, or **Compare Changes**.

3.  Choose How to Resolve
    - **Accept Current Change**: Keep your version (the code you wrote).
    - **Accept Incoming Change**: Take the other person’s version (the code from GitHub or main).
    - **Accept Both Changes**: Keep both sets of changes (VS Code will combine them; you may need to clean up extra lines).
    - **Compare Changes**: See a side-by-side view to understand the differences before deciding.

      **Tip:** If you’re not sure, click **Compare Changes** and read both versions. Pick the one that makes sense, or combine them manually.

### 4. Clean Up the File

- After accepting changes, read through the file and make sure it looks correct.
- Remove any leftover conflict markers (`<<<<<<<`, `=======`, `>>>>>>>`).
- Save the file (Ctrl+S).

### 5. Mark as Resolved

- In the Source Control panel, the file will now be ready to stage.
- Click the `+` to stage the resolved file.
- Write a commit message like `fix: resolve merge conflict in LoginController.java` and commit.

### 6. Finish the Merge

- Push your changes to GitHub.
- If you were merging main into your branch, continue working as usual.
- If you were merging a PR, GitHub will now let you finish the merge.

### 7. If You’re Stuck

- If you’re confused or the code looks wrong, **ask for help in the group chat**. Take a screenshot of the conflict and explain what you tried.

---

- **Forgot to pull before starting?**
  - You might get conflicts or your PR will look weird. Always pull the latest code before creating a branch.

- **Merge conflicts?**
  - See the section above for a full step-by-step guide. If you’re not sure how to fix them, ask for help.

- **Accidentally staged or committed the wrong files?**
  - Unstage by right-clicking the file in Source Control and choosing "Unstage". If you already committed, ask for help in the group chat.

- **CI/CD (red X) failed after pushing or PR?**
  - Click the red X to see the error details. If you don’t understand, ask in the group chat.

- **Can’t find your branch or PR?**
  - Make sure you pushed your branch. If it’s not on GitHub, click "Publish Branch" in VS Code.

- **Still stuck?**
  - Don’t waste time—ask in the group chat. Screenshots help!

```

```
