<?php
function getPasswordHash($password): string {
    if(is_null($password)){
        return "";
    }
    return password_hash($password, PASSWORD_DEFAULT);
}

function isPasswordSame($passString,$hashPass): bool{
    return password_verify($passString,$hashPass);
}
?>