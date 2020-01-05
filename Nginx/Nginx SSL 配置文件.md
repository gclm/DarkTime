```conf
server {
    listen       80;
    server_name  git.inskyuav.cn;
    rewrite ^ https://$http_host$request_uri? permanent;    #强制将http重定向到https
    server_tokens off;
}

server {
	
    listen 443 ssl;      # ssl http2 fastopen=3 reuseport;
    server_name git.inskyuav.cn;
    
    server_tokens off;

    ssl_certificate          /etc/nginx/ssl/fullchain.cer;
    ssl_certificate_key      /etc/nginx/ssl/inskyuav.cn.key;
    ssl_dhparam              /etc/nginx/ssl/dhparam.pem;
    
    ssl_session_timeout 5m;
    ssl_session_cache shared:SSL:5m;
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    ssl_ciphers 'ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-SHA384:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES256-SHA384:ECDHE-RSA-AES128-SHA256:ECDHE-RSA-AES256-SHA:ECDHE-ECDSA-AES256-SHA:ECDHE-RSA-AES128-SHA:ECDHE-ECDSA-AES128-SHA:DHE-RSA-AES256-GCM-SHA384:DHE-RSA-AES256-SHA256:DHE-RSA-AES256-SHA:DHE-RSA-CAMELLIA256-SHA:DHE-RSA-AES128-GCM-SHA256:DHE-RSA-AES128-SHA256:DHE-RSA-AES128-SHA:DHE-RSA-SEED-SHA:DHE-RSA-CAMELLIA128-SHA:HIGH:!aNULL:!eNULL:!LOW:!3DES:!MD5:!EXP:!PSK:!SRP:!DSS';
    ssl_prefer_server_ciphers on;
    proxy_set_header X-Forwarded-For $remote_addr;
    add_header Strict-Transport-Security "max-age=31536000; includeSubDomains";
    

    location / {
        proxy_pass         http://127.0.0.1:10000;
        proxy_set_header   Host $host;
        proxy_set_header   X-Real-IP $remote_addr;
        proxy_set_header   X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header   X-Forwarded-Host $server_name;
        proxy_set_header   X-Forwarded-Proto https;
        proxy_read_timeout  1200s;
        client_max_body_size 0;

    }

    access_log  /www/site/logs/git.inskyuav.cn/git.inskyuav.cn.log;
    error_log  /www/site/logs/git.inskyuav.cn/git.inskyuav.cn.error.log;

}
```