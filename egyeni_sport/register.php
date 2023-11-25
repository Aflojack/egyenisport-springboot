<?php
    require("util/PasswordController.php");
    require("dao/UserDAO.php");

    $error="";
    if(isset($_POST["felhasznalonev"]) && isset($_POST["jelszo"]) && isset($_POST["nev"]) && isset($_POST["admin"])){
        $user=new UserDAO();
        $pass=getPasswordHash($_POST["jelszo"]);
        $user->createUser($_POST["felhasznalonev"],$pass,$_POST["nev"],$_POST["admin"]==="1"?1:0);
        header("Location: login.php");
    }else{
        $error="Hiba a létrehozás során!";
    }
?>

<html lang="hu">
<head>
    <title>Regisztráció</title>
</head>
    <body>
        <form method="post" action="register.php">
            <label>
                Felhasználónév
                <input type="text" name="felhasznalonev">
            </label>
            <label>
                Jelszó
                <input type="password" name="jelszo">
            </label>
            <label>
                Teljes név
                <input type="text" name="nev">
            </label>
            <label>
                Admin
                <input type="text" name="admin">
            </label>
            <input type="submit" name="login" value="Elküld">
        </form>
        <?php echo "<p>" . $error . "</p>"; ?>
    </body>
</html>