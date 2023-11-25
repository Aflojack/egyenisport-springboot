<?php

class UserModel{
    private string $felhasznalonev;
    private string $jelszo;
    private string $nev;
    private boolean $admin;

    public function getFelhasznalonev(): string
    {
        return $this->felhasznalonev;
    }

    public function setFelhasznalonev(string $felhasznalonev): void
    {
        $this->felhasznalonev = $felhasznalonev;
    }

    public function getJelszo(): string
    {
        return $this->jelszo;
    }

    public function setJelszo(string $jelszo): void
    {
        $this->jelszo = $jelszo;
    }

    public function getNev(): string
    {
        return $this->nev;
    }

    public function setNev(string $nev): void
    {
        $this->nev = $nev;
    }

    public function isAdmin(): bool
    {
        return $this->admin;
    }

    public function setAdmin(bool $admin): void
    {
        $this->admin = $admin;
    }
}

?>