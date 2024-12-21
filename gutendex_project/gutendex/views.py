from django.http import JsonResponse

def book_list(request):
    data = {
        "count": 1,
        "next": None,
        "previous": None,
        "results": [
            {
                "id": 1,
                "title": "Example Book",
                "authors": [{"name": "Author Name", "birth_year": 1800, "death_year": 1870}],
                "subjects": ["Subject 1", "Subject 2"],
                "bookshelves": ["Bookshelf 1"],
                "languages": ["en"],
                "copyright": False,
                "media_type": "text",
                "formats": {"text/plain": "http://example.com/book.txt"},
                "download_count": 1500
            }
        ]
    }
    return JsonResponse(data)
