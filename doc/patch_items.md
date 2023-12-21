## Modifica annuncio, con patch

URL: `/items/:item_id`

Metodo: `PATCH`

Modifica l'annuncio con `id = item_id`,
modificando  solo i campi inviati in formato `json`.
Ritorna in formato `json` l'annuncio modificato.

## Richiesta

Content-Type: `application/json`

Corpo:

```json
{
  "title": "Annuncio 1 modificato con patch"
}
```

## Risposta con successo

Status code: `200 OK`

Content-Type: `application/json`

Esempio risposta:

```json
{
  "id": "f79c67c6-2aac-11ec-8d3d-0242ac130003",
  "title": "Annuncio 1 modificato con patch",
  "description": "Testo annuncio 1",
  "author": "Marco"
}
```

## Not Found

Se la risorsa con `id = item_id` non viene trovata.

Status code: `404 NOT FOUND`

## Bad Request
Status code: `400 BAD REQUEST`