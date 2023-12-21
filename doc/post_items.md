## Creazione annuncio

URL: `/items`

Metodo: `POST`

Crea un nuovo annuncio. Ritorna in formato `json` l'annuncio creato.
Viene associato all'annuncio un `id` univoco.

## Richiesta

Content-Type: `application/json`

Corpo:

```json
{
  "title": "Annuncio 1",
  "description": "Testo annuncio 1",
  "author": "Marco"
}
```

## Risposta con successo

Status code: `201 CREATED`

Content-Type: `application/json`

Esempio risposta:

```json
{
  "id": "f79c67c6-2aac-11ec-8d3d-0242ac130003",
  "title": "Annuncio 1",
  "description": "Testo annuncio 1",
  "author": "Marco"
}
```

## Bad Request
Status code: `400 BAD REQUEST`