## Modifica annuncio

URL: `/items/:item_id`

Metodo: `PUT`

Modifica l'annuncio con `id = item_id`. Ritorna in formato `json` l'annuncio modificato.

## Richiesta

Content-Type: `application/json`

Corpo:

```json
{
  "id": "f79c67c6-2aac-11ec-8d3d-0242ac130003",
  "title": "Annuncio 1 modificato",
  "description": "Testo annuncio 1",
  "author": "Marco"
}
```

## Risposta con successo

Status code: `200 OK`

Content-Type: `application/json`

Esempio risposta:

```json
{
  "id": "f79c67c6-2aac-11ec-8d3d-0242ac130003",
  "title": "Annuncio 1 modificato",
  "description": "Testo annuncio 1",
  "author": "Marco"
}
```

## Not Found

Se la risorsa con `id = item_id` non viene trovata.

Status code: `404 NOT FOUND`

## Bad Request
Status code: `400 BAD REQUEST`