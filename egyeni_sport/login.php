<?php
    include_once("util/PasswordController.php");

    $error="";
    if(isset($_POST["felhasznalonev"]) && isset($_POST["jelszo"])){

        header("Location: tables.php");
    }else{
        $error="Rossz felhasznalónév vagy jelszó";
    }
?>

<html lang="hu">
    <head>
        <title>Bejelentkezés</title>
    </head>
    <body>
    <form action="login.php" method="post">
        <label>
            Felhasználónév
            <input type="text" name="felhasznalonev">
        </label>
        <label>
            Jelszó
            <input type="password" name="jelszo">
        </label>
    </form>
    <?php echo "<p>" . $error . "</p>"; ?>
    </body>
</html>