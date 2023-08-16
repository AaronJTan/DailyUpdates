import Navbar from '@/components/Navbar'
import './globals.css'

export const metadata = {
  title: 'DailyUpdates',
  description: 'DailyUpdates',
}

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <Navbar />
        <div className="main-content">
          {children}
        </div>
      </body>
    </html>
  )
}
