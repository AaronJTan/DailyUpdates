import { NextResponse } from 'next/server'
 
export function middleware(request) {
  if (request.cookies.get("jwt")) {
    return NextResponse.redirect(new URL('/', request.url))
  }
}
 
export const config = {
  matcher: ["/signup", "/signin"]
}