# Удаление существующих SSH туннелей
Get-Process ssh | Stop-Process -Force

# Создаем SSH-туннели
Start-Process -NoNewWindow -FilePath "powershell" -ArgumentList "-Command", "ssh -L 5433:localhost:5433 root@193.168.49.199 -N"
# Даем время на установку туннелей
Start-Sleep -Seconds 2

# Проверка туннелей
$connection1 = Test-NetConnection -ComputerName localhost -Port 5433

if (-not$connection1.TcpTestSucceeded)
{
    Write-Host "ПРЕДУПРЕЖДЕНИЕ: TCP connect to (::1 : 5433) failed"
}
else
{
    Write-Host "SSH туннель для postgresauthservice успешно установлен."
}